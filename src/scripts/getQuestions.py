import time

import sys
from html import unescape

import requests
from requests.models import Response

import pyodbc
from pyodbc import Connection

_totalCall = 0
_successes = 0

# mssql connection parameters
_SERVER = "KIBREWOSSEN\\MSSQLSERVER01"
_DATABASE = "TRIVIA"
_USER = "trivia_user"
_PASS = "trivia"

# api urls for each category
_QUESTION_SIZE = 30
_GENERAL_API_URL = f"https://opentdb.com/api.php?amount={_QUESTION_SIZE}&category=9&type=multiple"
_MUSIC_API_URL = f"https://opentdb.com/api.php?amount={_QUESTION_SIZE}&category=12&type=multiple"
_MOVIE_API_URL = f"https://opentdb.com/api.php?amount={_QUESTION_SIZE}&category=11&type=multiple"
_MATH_API_URL = f"https://opentdb.com/api.php?amount={_QUESTION_SIZE}&category=19&type=multiple"

categories = [_GENERAL_API_URL, _MUSIC_API_URL, _MOVIE_API_URL, _MATH_API_URL]

def getConnection() -> Connection:
    _TRAIL = 3

    # Connection string
    connection_string = f"DRIVER={{ODBC Driver 17 for SQL Server}};SERVER={_SERVER};DATABASE={_DATABASE};UID={_USER};PWD={_PASS};Encrypt=yes;TrustServerCertificate=yes;"
    
    try:
        connection = pyodbc.connect(connection_string)
        print("Connection successful!")
        return connection
    
    except pyodbc.Error as _:
        if _TRAIL <= 0:
            print("Failed connecting to database")
            sys.exit()

        time.sleep(1)
        _TRAIL -= 1

def addToDataBase(response: Response) -> None:
    global _totalCall   
    global _successes  

    if not response:
        return
    
    connection = getConnection()

    if connection:
        _QUERY = "INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices) values (?, ?, ?, ?, ?)"
        cursor = connection.cursor()

        questions = response['results']

        for question in questions:
            questionText = unescape(question['question'])
            correctAnswer = unescape(question['correct_answer'])
            incorrectAnswers = unescape(question['incorrect_answers'])
            choices = f'{correctAnswer}, {",".join(incorrectAnswers)}'
            
            category = unescape(question['category'])
            
            # replace category names for db rqrmt
            if category == "Entertainment: Music": category = "Music"
            elif category == "Entertainment: Film": category = "Movie"
            elif category == "Science: Mathematics": category = "Math"
            elif category == "General Knowledge": category = "General"

            try:
                cursor.execute(_QUERY, (questionText, correctAnswer, category, "MCQ", choices))
                _successes += 1
                print(f"{_successes} questions added to DB.")

            except pyodbc.Error as e:
                pass

            _totalCall += 1

        # close connection
        connection.commit()
        cursor.close()
        connection.close()

# function to get api response
def getResponse(url: str) -> Response:
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()

        return data
    
    else:
        time.sleep(1)
    
if __name__ == "__main__":
    for category in categories:
        response = getResponse(category)
        addToDataBase(response)

    print(f"{_successes}/{_totalCall} questions added!")

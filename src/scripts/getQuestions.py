import time
import os

import sys
from html import unescape

import requests
from requests.models import Response

import pyodbc
from pyodbc import Connection

totalCall = 0
successes = 0

# mssql connection parameters
SERVER = os.getenv("SERVER")
DATABASE = os.getenv("DATABASE")
USER = os.getenv("USER")
PASS = os.getenv("PASSWORD")

# api urls for each category
QUESTION_SIZE = 30
GENERAL_API_URL = f"https://opentdb.com/api.php?amount={QUESTION_SIZE}&category=9&type=multiple"
MUSIC_API_URL = f"https://opentdb.com/api.php?amount={QUESTION_SIZE}&category=12&type=multiple"
MOVIE_API_URL = f"https://opentdb.com/api.php?amount={QUESTION_SIZE}&category=11&type=multiple"
MATH_API_URL = f"https://opentdb.com/api.php?amount={QUESTION_SIZE}&category=19&type=multiple"

categories = [GENERAL_API_URL, MUSIC_API_URL, MOVIE_API_URL, MATH_API_URL]

def getConnection() -> Connection:
    TRAIL = 3

    # Connection string
    connection_string = f"DRIVER={{ODBC Driver 17 for SQL Server}};SERVER={SERVER};DATABASE={DATABASE};UID={USER};PWD={PASS};Encrypt=yes;TrustServerCertificate=yes;"
    
    try:
        connection = pyodbc.connect(connection_string)
        print("Connection successful!")
        return connection
    
    except pyodbc.Error as _:
        if TRAIL <= 0:
            print("Failed connecting to database")
            sys.exit()

        time.sleep(1)
        TRAIL -= 1

def addToDataBase(response: Response) -> None:
    global totalCall   
    global successes  

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
                successes += 1
                print(f"{successes} questions added to DB.")

            except pyodbc.Error as e:
                pass

            totalCall += 1

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

    print(f"{_successes}/{totalCall} questions added!")

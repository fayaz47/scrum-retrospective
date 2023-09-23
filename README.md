# scrum-retrospective
This repo used for only SIS test

This project used to add Retorspective , Get Retrospective and update Retrospective

Used Technologies Java17 and Srpingboot3

**Endpoint 1:  Add Retro**
/retro

Request:
{
"name" : "aa270bc4-0c35-11ee-be56-0242ac120002",
"summary": "First Retro2",
"date" : "2023-09-25",
"names" : [
"raju",
"rahim",
"john"
],
"feedback" : "NEGATIVE"

}

Response :

{
"name" : "aa270bc4-0c35-11ee-be56-0242ac120002",
"summary": "First Retro2",
"date" : "2023-09-25",
"names" : [
"raju",
"rahim",
"john"
],
"feedback" : "NEGATIVE"

}
**Endpoint2: Fetch retros**
/retros

Response :
{
{
"name" : "aa270bc4-0c35-11ee-be56-0242ac120001",
"summary": "First Retro1",
"date" : "2023-09-25",
"names" : [
"raju",
"rahim",
"john"
],
"feedback" : "POSITIVE"

}
{
"name" : "aa270bc4-0c35-11ee-be56-0242ac120002",
"summary": "First Retro2",
"date" : "2023-09-25",
"names" : [
"raju",
"rahim",
"john"
],
"feedback" : "NEGATIVE"

}
}
Endpoint3: **Update Feedback**
/retro?retroId={retroId}

Request:

{
"name" : "aa270bc4-0c35-11ee-be56-0242ac120002",
"summary": "First Retro2",
"date" : "2023-09-25",
"names" : [
"raju",
"rahim",
"john"
],
"feedback" : "NEGATIVE"

}

Response:

{
"name" : "aa270bc4-0c35-11ee-be56-0242ac120002",
"summary": "First Retro2",
"date" : "2023-09-25",
"names" : [
"raju",
"rahim",
"john"
],
"feedback" : "NEGATIVE"

}
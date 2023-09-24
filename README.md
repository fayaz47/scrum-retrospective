# scrum-retrospective
This repo used for only SIS test

This project used to add Retorspective , Get Retrospective and update Retrospective

Used Technologies Java17 and Srpingboot3

**Endpoint 1:  Add Retro**
/retro

Request:
{
"summary": "First Retro1",
"date": "2023-09-25",
"participants": [
"raju",
"rahim",
"john"
]

}

Response :

{
"name": "9fb69800-1007-4857-98bf-4b7dc2948ecf",
"summary": "First Retro1",
"date": "2023-09-25",
"participants": [
"rahim",
"john",
"raju"
],
"feedback": null
}
**Endpoint2: Fetch retros By Page**
/retros?pageSize=5&pageNo=0

Response :
{
"content": [
{
"name": "ceb12b97-c32e-4251-95f9-cf7a1080fca9",
"summary": "First Retro1",
"date": "2023-09-25T00:00:00.000+00:00",
"participants": [
"rahim",
"john",
"raju"
],
"feedback": []
}
],
"pageable": {
"pageNumber": 0,
"pageSize": 5,
"sort": {
"empty": true,
"unsorted": true,
"sorted": false
},
"offset": 0,
"paged": true,
"unpaged": false
},
"last": true,
"totalPages": 1,
"totalElements": 1,
"first": true,
"size": 5,
"number": 0,
"sort": {
"empty": true,
"unsorted": true,
"sorted": false
},
"numberOfElements": 1,
"empty": false
}

Endpoint 3 : **Retrieve retro by Date**
ULR : /retro?date=2023-09-25
Response :

[
{
"name": "51930f9b-8711-4655-b2d8-bd11d50b8924",
"summary": "First Retro1",
"date": "2023-09-25",
"participants": [
"rahim",
"john",
"raju"
],
"feedback": []
},
{
"name": "2dcb0cd5-3d8c-46c8-941d-ae212c6b3055",
"summary": "First Retro1",
"date": "2023-09-25",
"participants": [
"rahim",
"john",
"raju"
],
"feedback": []
},
{
"name": "9fb69800-1007-4857-98bf-4b7dc2948ecf",
"summary": "First Retro1",
"date": "2023-09-25",
"participants": [
"rahim",
"john",
"raju"
],
"feedback": []
}
]

Endpoint4: **Add Feedback to Retro**
/retro?retroId={retroId}

Request:

{
"name": "raju",
"body": "test",
"feedbackType": "negative"
}

Response:

{
"name": "cece75c8-f240-47b5-834b-1d86008df28a",
"summary": "First Retro1",
"date": "2023-09-25T00:00:00.000+00:00",
"participants": [
"rahim",
"john",
"raju"
],
"feedback": [
{
"id": "4b612189-43f4-43af-8995-180f15bcdf78",
"name": "raju",
"body": "test",
"feedbackType": "negative"
}
]
}

Endpoint 5 : **update Feedback to Retro**

Request :
{
"name": "raju",
"body": "test",
"feedbackType": "positive"
}

Response : 204 No Content
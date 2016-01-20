import datetime  
import pprint  
import pymongo  

from pymongo import MongoClient  

client = MongoClient('mongodb://localhost:27017/')  
db = client.mydb  

import datetime  
import pprint  
import pymongo  

from pymongo import MongoClient  

client = MongoClient('mongodb://192.168.1.3:27017/')  
db = client.imdb  

# results = db.col1.aggregate([
# {$match: {'movies.list.genre': 1}},
# {$project: {
#     shapes: {$filter: {
#         input: '$movies',
#         as: 'movie',
#         cond: {$eq: ['$$movies.list.genre', 'Short']}
#     }},
#     _id: 0
# }}
# ])
# results = db.col1.find({}, {'movies.list.genres':1, 'movies.list.descripion':1, '_id':0})
results = db.col1.find(
	{
		'movies.list.genres' : { '$exists': True }
	},
	{
	 	'movies.list.genres' : 1,
		'movies.list.descripion' : 1,
		'_id' : 0
	})

for result in results:
	print result
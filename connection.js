var MongoClient = require('mongodb').MongoClient;

var db = null;


const DATABASE_URL = "mongodb+srv://admin-aditya:messiisgreat@learning-sqrmr.mongodb.net/test?retryWrites=true&w=majority";



module.exports = {
	connect : () =>{
		return new Promise((resolve,reject)=>{
			MongoClient.connect(DATABASE_URL,{useNewUrlParser:true, useUnifiedTopology: true},(err,client)=>{
				if(err){
					reject(err);
				}else{
					db = client.db("WomenSafety");
					//console.log(db)
					resolve();
				}
			})
		})
	},
	get : ()=>{
		return db;
	},
	
	close: async (client) => {
	  //client.close();
	  return true;
	}
	};



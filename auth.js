var express = require('express');
var app = express();
var db = require("./connection").get();
var au = null;
app.use(express.json());


module.exports = {
    authentication : (email, pass) => {
        //console.log(db);
        return new Promise((resolve,reject) => {
            db.collection("users").find({email: email , password: pass}).toArray((err,result)=>{
                if(err ){
                    reject();
                }
    
                else{
                    if(result.length == 0){
                        au = false;
                    }
                    else{
                        au = true;
                    }
                    resolve();
                }
            });

            
        })
    },

    get : () => {
        console.log(au);
        return au;
    }
};
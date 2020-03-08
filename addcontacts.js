var express = require('express');
var app = express();
var db = require("./connection").get();

module.exports = {

    insertContacts : (mobile, contacts) => {
        return new Promise((resolve,reject) => {
            
            await db.collection("users").updateOne( {phone:mobile}, {$set: {contacts : contacts} }, (err,result)=>{
               if(err){
                   console.log("Some error occured!");
                   reject();
               }

               else{
                   console.log("contacts added!");
                   resolve();
               }
            });



        });
    }
}
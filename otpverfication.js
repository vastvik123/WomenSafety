var express = require('express');
var app = express();
var db = require("./connection").get();


const accountSid = 'ACcd9e532eda6576142ae137f0f2b46050';
const authToken = 'e85bec94750f5461946b425d733a708d';
const client = require('twilio')(accountSid, authToken);

const otp = Math.floor(Math.random()*10000).toString();




module.exports = {

    sendotp : (contact) => {
        return new Promise((resolve,reject) => {
            
            client.messages
            .create({
            body: 'your otp is: '+ otp,
            from: '+18564223624',
            to: '+91'+contact
            });

        resolve();


        });
    },

    checkotp : (x) => {
            if(x == otp)
            return true;

            else
            return false;
    },

    otp

}
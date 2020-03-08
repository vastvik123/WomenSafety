
(
    async ()=>{
        try{
            var express =  require("express");
            var app = express();

            var connection = require('./connection')

            app.use(express.json());
            await connection.connect()
            console.log("Connected")

            var auth = require('./auth');
            
            var otpverify = require('./otpverfication');

            var addcontacts = require('./addcontacts');

            app.get('/', (req,res) => {
                res.send("HI");
            });

            app.post('/postdata', async (req,res) => {
                console.log("Data received");
                var email = req.body.email;
                var pass =  req.body.password;
                console.log(email);
                console.log(pass);

                var check = await auth.authentication(email,pass);
                check = auth.get();
                if(check == true){
                    console.log("success");
                    res.status(200).json({
                        result: "Success"
                    });
                }
                else{
                    console.log("Invalid Email or Password");
                    res.status(200).json({
                        result: "Invalid Email or Password"
                    });
                }
            });



            app.post('/contact', async (req,res) => {
                console.log("Contact number received");
                var contact = req.body.mobile;
                
                console.log(contact);
                
                var sendotp = await otpverify.sendotp(contact);

                console.log("OTP sent")
                var otp = otpverify.otp.toString();
                res.status(200).json({
                    OTP: otp
                });
                
            });


            app.post('/verifyotp', async (req, res) => {
                console.log("otp recevied and now sent for checking");
                var receivedotp = req.body.otp;

                var checkotp = await otpverify.checkotp(receivedotp);

                if(checkotp == true){
                    console.log("Correct otp entered!")
                    res.status(200).json({
                        result: "Success"
                    });
                }

                else{
                    console.log("Wrong otp entered!");
                    res.status(200).json({
                        result: "Invalid otp"
                    });
                }
            });



            app.post('/addcontacts', async (req,res) => {

                
                var p = req.body.mobile;

                var con1 = req.body.Contact1;
                var con2 = req.body.Contact2;
                var con3 = req.body.Contact3;
                var con4 = req.body.Contact4;
                var con5 = req.body.Contact5;

                var con = [ con1, con2, con3, con4, con5];
                
                console.log(p);
                console.log(con1);
                //console.log(con);

                addcontacts.insertContacts(p,con);
                res.status(200).json({
                    result: "Success"
                });

            });


            app.listen(3000);
        }catch(e){  
            console.log(e)
        }
    }
)()




const express = require ('express');
const app = express();

const onLog = require ('./routes/onLog');
const onInit = require ('./routes/onInit');
const stage = require ('./routes/stage');
//const store = require('./routes/store')
// app.use('/onLogin', (req,res)=>{

//     const data = {
//         last : "aaads",
//         first: "bbbdss"
//     };
//     res.json(data);
// })



app.use ('/onLog', onLog)
app.listen(3001, ()=> console.log(`Node.js Server is running on port 3001`));

app.use ('/onInit', onInit)
app.listen(3002, ()=> console.log(`Node.js Server is running on port 3002`));

app.use ('/stage', stage)
app.listen(3003, ()=> console.log(`Node.js Server is running on port 3003`));

app.listen(3004, ()=> console.log(`Node.js Server is running on port 3004`));
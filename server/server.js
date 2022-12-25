const express = require ('express');
const app = express();

const onLog = require ('./routes/onLog');
const onInit = require ('./routes/onInit');
const stage = require ('./routes/stage');
const submit = require ('./routes/submit');
const tmp = require ('./routes/tmp');
const check = require ('./routes/check');
const show = require ('./routes/show');
const result = require ('./routes/result');
const fileManagement = require ('./routes/fileManagement');
const ranking = require ('./routes/ranking')
//const store = require('./routes/store')
// app.use('/onLogin', (req,res)=>{

//     const data = {
//         last : "aaads",
//         first: "bbbdss"
//     };
//     res.json(data);
// })

app.use ('/tmp', tmp)
app.use ('/submit', submit)
app.use ('/onLog', onLog)

app.use ('/onInit', onInit)

app.use ('file', fileManagement)

app.use ('/stage', stage)
    app.use ('/check', check)
    app.use ('/show', show)
    app.use ('/result', result)
    app.use ('/ranking', ranking)

app.listen(3001, ()=> console.log(`Node.js Server is running on port 3001`));
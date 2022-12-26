const express = require('express');
const { flushSync } = require('react-dom');
const fs = require ('fs');
const router = express.Router();



const dir = './src/db/plays/onGoing/tmp/'



    //get post 차이
let who 
router.post('/0/set', async(req, res)=>{
   
    who = req.query.WHO
    return res.send ({"RESULT_CODE": 1, "NAME": req.query.NAME})

})

router.post('/0/get', async(req, res)=>{
    console.log ("누구",who)
    const name = who + ".json"
    const data = fs.readFileSync(dir+name, {encoding:"utf-8"})
    return res.send (data)

})

module.exports =router;
const express = require('express');
const { flushSync } = require('react-dom');
const fs = require ('fs');
const router = express.Router();



const dir = './src/db/plays/onGoing/tmp/'



    //get post 차이
let who 
router.post('/0/set', async(req, res)=>{
    console.log ("누구이름aa",req.query.WHO)
    who = req.query.WHO
    return res.send ({"RESULT_CODE": 1, "NAME": req.query.NAME})
    //return res.send (JSON.stringify(obj))

})

router.post('/0/get', async(req, res)=>{
    console.log ("누구이름bb",req.query.WHO)
    const name = who + ".json"
    const data = fs.readFileSync(dir+name, {encoding:"utf-8"})
    console.log ("데이터터", data)
    return res.send (data)
    //return res.send (JSON.stringify(obj))

})

module.exports =router;
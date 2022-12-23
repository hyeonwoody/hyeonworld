const express = require('express');
const router = express.Router();
const fs = require ('fs');


const dir = "./src/db/plays/onGoing/"
const map = new Map();

const GAME0 = 2

router.post('/0/calculate', async(req, res)=>{
    console.log ("계싼싼",req.query.NAME)
    var correct = (req.query.VALUE%7 === 0)
        console.log("before ",map)
        map.set(req.query.NAME, correct)
        console.log("after ", map)
        return res.send ({"RESULT_CODE": 0})
});

router.post('/0/get', async (req, res)=>{
    console.log("서버 리절트aaa")
    console.log("dfd",map)
    var data = []
    map.forEach((value, key, map)=>{
        if (value){
            data.push(key)
            let addNew = true
            if (fs.existsSync(dir+"logged.json")){
                let data1 = fs.readFileSync(dir+"logged.json", {encoding:"utf-8"})
                let db = JSON.parse(data1)
                db.players.forEach((item,i)=>{
                    
                    if (key === item.name){
                        item.GAME0SCORE += 2
                    }
                })
                
                fs.writeFileSync (dir+"logged.json", JSON.stringify(db))
            }
            else {
                var obj = {
                    NAME: key,
                    SCORE: GAME0
                }
                fs.writeFileSync(dir+"logged.json", JSON.stringify(obj))
            }
        }
        

    });
    console.log(data)
    return res.send (data)

})
    

module.exports =router;
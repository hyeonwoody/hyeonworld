const express = require('express');
const router = express.Router();
const fs = require ('fs');

const DB = "./src/db/plays/onGoing/logged.json"
const tmpDB = "./src/db/plays/onGoing/tmp/game00Score.json"

GAME0CORRECTSCORE = 3
GAME0MISSINGSCORE = 2


router.post('/result/0', async (req, res)=>{
            console.log("왔어")
             var name = req.query.NAME
            if (fs.existsSync(tmpDB)){
                let data = fs.readFileSync(tmpDB, {encoding:"utf-8"})
                let db = JSON.parse(data)
                db.forEach((item)=>{
                    
                    if (name === item.name){
                        item.game0Score += GAME0SCORE
                    }
                })
                fs.writeFileSync (tmpDB, JSON.stringify(db))
            }
            else {
                let data = fs.readFileSync(DB, {encoding:"utf-8"})
                let db = JSON.parse(data)
                let movedb = []
                db.players.forEach((item)=>{
                    if (key === item.name){
                        item.game0Score += GAME0SCORE
                    }
                    movedb.push ({"name": item.name, "game00Score" : item.game00Score})
                })
                fs.writeFileSync (tmpDB, JSON.stringify(movedb))
            }
    console.log(data)
    return res.send (data)

});
    

module.exports =router;
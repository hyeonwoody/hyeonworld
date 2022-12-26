
const express = require('express');
const router = express.Router();
const router2 = express.Router()
const fs = require ('fs');
const { stringify } = require('querystring');

const tmpDB = "./src/db/plays/onGoing/tmp/game00Score.json"
const correcter = new Map();
let whos = ""
let afalse = '0'
GAME0CORRECTSCORE = 3
GAME0MISSINGSCORE = 2

router.post('/0/init', (req,res)=>{
    console.log("이니셜라이징징")
    correcter.clear()
    whos = req.query.WHOS
    afalse = req.query.AFALSE
});

router.post ('/0/set/whos', async (req,res)=>{
    correcter.set (whos, 2)
})

router.post('/0/calculate', async(req, res)=>{
    var correct = (req.query.ANSWER === afalse)
    
    if (whos !== req.query.NAME){
        var val = 0
        if (correct){
            console.log ("#result/0/calculate  정답 [첫번째 게임 제출자 : "+req.query.NAME+"]")
            console.log ("#result/0/calculate  정답 [첫번째 게임 제출답안 : "+0+"]")
            val = 1
        }
        else {
            console.log ("#result/0/calculate 노답 [첫번째 게임 제출자 : "+req.query.NAME+"]")
            console.log ("#result/0/calculate 노답 [첫번째 게임 제출답안 : "+req.query.ANSWER+"]")
        }
        correcter.set(req.query.NAME, val)
        console.log(correcter)
    }
    return res.send ({"RESULT_CODE": 0})
});

router.post('/0/get', async (req, res)=>{
    var count = 0
    var db
    var yes = [] 
    const flag = parseInt(req.query.FLAG)
    correcter.set("왔다")
    correcter.forEach((correct, name, map)=>{
        if (correct === 0){
            
            count++
        }
        else if (correct === 1){
            yes.push (name)
            let push = true
            var data
            if (flag === 45678){
                if (fs.existsSync(tmpDB)){
                    data = fs.readFileSync(tmpDB, {encoding:"utf-8"})
                }
                else {
                    db1 = []
                    db1.push ({"name": "a", "game00Score" : 0, "scoreReason": "^game00_Missing_"})
                    fs.writeFileSync (tmpDB, JSON.stringify(db1))
                    data = fs.readFileSync(tmpDB, {encoding:"utf-8"})
                }
                db = JSON.parse(data)

                console.log("맞음음")
                console.log("길이이",db.length)
                if (db.length){
                    db.forEach((item)=>{
                        if (name === item.name){
                            console.log("ㄹㄹㄹㄹㄹㄹㄹ")
                            item.game00Score += GAME0CORRECTSCORE
                            item.scoreReason += "^game00_From_"+whos
                            push = false
                        }
                    })
                }
                if (push){
                    console.log("ㄹㄹㄹㄹㄹㄹㄹㅁ")
                    db.push ({"name": name, "game00Score" : GAME0CORRECTSCORE, "scoreReason": "^game00_From_"+whos})
                }
                fs.writeFileSync (tmpDB, JSON.stringify(db))
            }
                
        }
        else if (correct === 2){
            console.log("코렉트 투투투퉅투투투")
            let push = true
            var data
            if (flag === 45678){
                if (fs.existsSync(tmpDB)){
                    data = fs.readFileSync(tmpDB, {encoding:"utf-8"})
                }
                else {
                    db1 = []
                    db1.push ({"name": "a", "game00Score" : 0, "scoreReason": "^game00_Missing_"+count})
                    fs.writeFileSync (tmpDB, JSON.stringify(db1))
                    data = fs.readFileSync(tmpDB, {encoding:"utf-8"})
                }
                db = JSON.parse(data)
                if (db.length){
                    db.forEach((item)=>{
                        if (name === item.name){
                            item.game00Score += GAME0MISSINGSCORE * count
                            item.scoreReason += "^game00_Missing_"+count
                            push = false
                        }
                    })
                }
                if (push){
                    db.push ({"name": name, "game00Score" : GAME0MISSINGSCORE * count, "scoreReason": "^game00_Missing_"+count})
                }
                fs.writeFileSync (tmpDB, JSON.stringify(db))
            }  
        }
    })
    console.log("ff",yes)
    return res.send (yes)
});
    

module.exports =router;
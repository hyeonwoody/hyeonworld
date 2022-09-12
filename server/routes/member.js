const express = require('express');
const router = express.Router();
//const db = require('../../src/config/db');
const fs = require ('fs');
const { stringify } = require('querystring');

let static_value = (function static_func (value){
    let i = value;
    return function() {
        return ++i;
    }
})(0); // 정적 변수 시작을 0으로 초기화

// const db = {
//     "members": [
//         {"name":"정덕수", "score": 23},
//         {"name":"윤순덕", "score": 3},
//         {"name":"정현우", "score": 3},
//         {"name":"카", "score": 3},
//         {"name":"오랑캐", "score": 3}
//     ],
//     "games":[
//         {"name": "2 truth 1 lie"}
//     ],
//     "va": "nma",
//     "ta": "cxv"
// }


function increment(name) {
    let cnt = static_value();
    console.log(cnt);

    const data = fs.readFileSync ("./src/config/live.json", {encoding:"utf-8"})
    let db = JSON.parse(data)
    for (key in db.member){       
        if (name === db.member[key].name){
            db.member[key].loggedIn = 1
            console.log ("first", db)   
            fs.writeFileSync ("./src/config/live.json", JSON.stringify (db), function reviver(key, value){
                return (value == 'null' || value == 'undefined') ? false : value;
            });
            return 1
        }
    }
    
    db.member.push ({"name":name, "entry": String(cnt), "loggedIn": '1', "score": 0})
    console.log ("second " ,db)   
    fs.writeFileSync ("./src/config/live.json", JSON.stringify (db), (err)=>{
        if (err)
            console.log  (err)
    });
}


router.post ('/onLogin',  (req,res) => {
    const name = req.query.name

    const data = fs.readFileSync("./src/config/db.json", {encoding: "utf-8"})
    let db = JSON.parse(data)
    if (name === "어드minister")
        return res.send({'resultcode': 2})
    else if (name === "화면monitor")
    return res.send({'resultcode': 3})
    for (key in db.members){
        if (name === db.members[key].name){
           increment(name)
            return res.send({'resultcode': 1})
        }
    }
    return res.send({'resultcode': 0})
})

router.post ('/onLogout', async (req,res) => {
    const name = req.query.NAME

    const data = fs.readFileSync ("./src/config/live.json", {encoding:"utf-8"})
    let db = JSON.parse(data, function reviver(key, value){
        return (value == 'null') ? false : value;
    }) 
    for (key in db.member){       
        console.log ( "THIS : ",name)   
        db.member[key] == null && delete db.member[key]
        if (name === db.member[key].name){
            db.member[key].loggedIn = '0' 
            
            fs.writeFileSync ("./src/config/live.json", JSON.stringify (db), function reviver(key, value){
                return (value == 'null' || value == 'undefined') ? false : value;
            });
            
            console.log (db)   

            return res.send({'resultcode':1})
        }
    }
    return res.send({'resultcode': 0})
})

router.post ('/onConfig', async (req,res) => {
    const name = req.query.NAME

    const data = fs.readFileSync ("./src/config/live.json", {encoding:"utf-8"})
    let db = JSON.parse(data, function reviver(key, value){
        return (value == 'null') ? false : value;
    }) 

    db.config.push(req.query)
    fs.writeFileSync ("./src/config/live.json", JSON.stringify (db), function reviver(key, value){
        return (value == 'null' || value == 'undefined') ? false : value;
    });      
            console.log (db)   

            return res.send({'resultcode':1})
})

router.post ('/onSubmit0', async (req,res) => {
    const name = req.query.name
    const Qfirst = req.query.first
    const Qsecond = req.query.second
    const Qthird = req.query.third
    const iFalse = req.query.iFalse

    console.log ("ON SUBMIT : ", name, Qfirst, Qsecond, Qthird, iFalse)
    const data = fs.readFileSync ("./src/config/live.json", {encoding:"utf-8"})
    let db = JSON.parse(data)

    const insert = {
        "name": name,
        "Qfirst": Qfirst,
        "Qsecond": Qsecond,
        "Qthird": Qthird,
        "iFalse": iFalse
    }
    db.truth.push(insert)
    // for (key in db.truth){
    //      if (name === db.truth[key].name){
    //         console.log("daabase here : ", db.member[key].name)
    //         db.truth[key]=insert
    //     }
    // }
    fs.writeFileSync ("./src/config/live.json", JSON.stringify (db), function reviver(key, value){
        return (value == 'null' || value == 'undefined') ? false : value;
    });      
    console.log ("final",db)   
    return res.send({'resultcode':1})
})


router.post ('/truth', (req,res) => {
    const data = fs.readFileSync ("./src/config/live.json", {encoding:"utf-8"})
    let db = JSON.parse(data, function reviver(key, value){
        return (value == 'null') ? false : value;
    }) 
    return res.send(db.truth)
})

let id = -1
router.post ('/truthShow', (req,res) => {
    
    const data = fs.readFileSync ("./src/config/live.json", {encoding:"utf-8"})
        let db = JSON.parse(data)
    console.log("/truthShow db: ", db)
    if (req.query.ID){
        
    console.log("/truthShow in query: ", req.query.ID)
    console.log("/truthShow in id: ", db.truth[req.query.ID])
        id = parseInt(req.query.ID)
        
    }

    console.log("/truthShow out query: ", req.query.ID)
    console.log("/truthShow out id: ", db.truth[req.query.ID])
    return res.send(db.truth[id])
})

router.post ('/truthAnswer', (req, res) => {
    const data = fs.readFileSync ("./src/config/live.json", {encoding:"utf-8"})
    let db = JSON.parse(data)
    if (db.truth[id].iFalse === req.query.ID){
        console.log ("FALSE :")
        return res.send ({"resultcode": 1})
    }
    else {
        return res.send ({"resultcode": 0})
    }
})

router.post ('/truthCorrect', (req, res) => {


    if (req.query.Correct){
        const name = req.query.NAME
        const data = fs.readFileSync ("./src/config/live.json", {encoding: "utf-8"})
        let db = JSON.parse (data)
        for (key in db.member){      
            console.log("truth LOg", db.member[key])
            console.log("aaaa : ", db.memberp[key].name)
         if (name === db.member[key].name){
            db.member[key].score = db.member[key].score + 3 
            fs.writeFileSync ("./src/config/live.json", JSON.stringify (db))
            console.log (db)   
        }
    } 
    }
    
})

module.exports = router;
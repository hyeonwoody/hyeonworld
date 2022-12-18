const express = require('express');
const router = express.Router();
const fs = require ('fs');


const onGoing = "./src/db/plays/onGoing/"
    let id = 0
    let double = false
const file = onGoing + "logged.json";


router.post ('/in', async (req,res)=>{
    
    const name = req.query.NAME
    let resultCode = 0

    const data = fs.readFileSync("./src/db/members.json", {encoding:"utf-8"})
    let db = JSON.parse (data)
    if (name === "어드min")
        resultCode = 2
    else if (name === "화면monitor")
        resultCode = 3
    for (key in db.members){
        
        if (name === db.members[key].name){ 
            resultCode = 1
            
            const data1 = fs.readFileSync(file, {encoding:"utf-8"})
            db = JSON.parse (data1)    
            let push = true
            for (j in db.players){
                if (db.players[j].name === name){
                    resultCode = 4
                    push = false
                    break;
                }
            }
            if (push){
                db.players.push({id:id++, name:name, login: true})
                double = false
            }
                    
            fs.writeFileSync (file, JSON.stringify(db))
            
            break;
        }
    }

    return res.send ({"RESULT_CODE": resultCode})
})

router.post ('/out', (req,res)=>{
    const name = req.query.NAME;
    let resultCode = 1
    
    const onGoing = "./src/db/plays/onGoing/"
    fs.readdir (onGoing, (err, data)=>{
        if (err) return console.log(err);

        const data2 = fs.readFileSync (file, {encoding:"utf-8"})
        db = JSON.parse (data2)

        for (j in db.players){
            if (db.players[j].name === name){
                db.players[j].login = false
                db.players.splice(j,1) //delete
            }
        }

        fs.writeFileSync (file, JSON.stringify(db))
    });

    return res.send ({"RESULT_CODE": resultCode})
})

module.exports =router;
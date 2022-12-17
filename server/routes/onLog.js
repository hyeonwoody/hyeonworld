const express = require('express');
const router = express.Router();
const fs = require ('fs');

    let id =0

function dataEdit(name){
    const onGoing = "./src/db/plays/onGoing/"
    fs.readdir (onGoing, (err, data)=>{
        if (err) return console.log(err);
        id++
        data.forEach((item, i)=>{
            file = onGoing + item;
            const data = fs.readFileSync(file, {encoding:"utf-8"})
            db = JSON.parse (data)

            db.init.players.push({id:id, name:name, login: true})
            // db.init.players = {...db.init.players[z],
            //     id: z++,
            //     name: name
            // }

            
            fs.writeFileSync (file, JSON.stringify(db))
        });
    });
    
}

router.post ('/in', (req,res)=>{

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
            dataEdit(name)
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
        
        data.forEach((item, i)=>{
            file = onGoing + item;
            const data = fs.readFileSync(file, {encoding:"utf-8"})
            db = JSON.parse (data)
            
            for (j in db.init.players){
                if (db.init.players[j].name === name){
                    db.init.players[j].login = false
                    db.init.players.splice(j,1)
                }
            }
            
            fs.writeFileSync (file, JSON.stringify(db))
        });
    });

    return res.send ({"RESULT_CODE": resultCode})
})

module.exports =router;
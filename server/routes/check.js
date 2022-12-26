const express = require('express');
const router = express.Router();
const fs = require ('fs');


const onGoing = "./src/db/plays/onGoing/"


router.post ('/0', async (req,res)=>{
    const data = fs.readFileSync(onGoing+"logged.json", {encoding:"utf-8"})
    let db = JSON.parse (data)
    var names = [];
        db.players.forEach ((item, i)=>{
            names.push(item.name)
        });
    
    var lia = []
    const data2 = fs.readFileSync(onGoing+"tmp/"+"example.json", {encoding:"utf-8"})
    lia.push (data2)
    names.forEach ((item)=>{
        if (fs.existsSync(onGoing+"tmp/"+item+".json")){
            const data1 = fs.readFileSync(onGoing+"tmp/"+item+".json", {encoding:"utf-8"})
            lia.push (data1)
        }
    })    
    // for (num in db.players){
    //     console.log("플레이어",player[])
    //     game0 = {
    //         ...game0,
    //         player
    //     }
        
    // }
    return res.send (lia)
});

module.exports =router;
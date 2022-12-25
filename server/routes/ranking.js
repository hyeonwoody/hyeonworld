const express = require('express');
const router = express.Router();
const fs = require ('fs');


const onGoing = "./src/db/plays/onGoing/"
const DB = "./src/db/plays/onGoing/logged.json"
const tmpDB = "./src/db/plays/onGoing/tmp/game00Score.json"

router.post ('/0', async (req,res)=>{
        var ranking = []

    if (fs.existsSync(tmpDB)){
        const data = fs.readFileSync (tmpDB, {encoding:"utf-8"})

        ranking = JSON.parse(data)
        console.log("before",ranking)
                console.log("타입입",typeof(ranking))
                for (let i = 0; i < ranking.length; i++){
                    
                    for (let j = i+1; j < ranking.length; j++){
                        console.log("왔어어zzzz",typeof(ranking[i].game00Score))
                        console.log("왔어어zzzzaaaaaa", typeof(ranking[j].game00Score))
                        if (ranking[j].game00Score > ranking[i].game00Score){
                            console.log("왔어어z", i, j)
                            var tmp = ranking[i]
                            ranking[i] = ranking[j]
                            ranking[j] = tmp
                        }
                        else {
                            console.log("zzzz왔어어",ranking[i].game00Score)
                            console.log("zzzz왔어어",ranking[j].game00Score)
                        }
                    }
                }
                console.log("after", ranking)     
    }
    else if (fs.existsSync(onGoing+"tmp/game00Score.json")){
        const data = fs.readFileSync (onGoing+"tmp/game00Score.json", {encoding:"utf-8"})
        ranking.push (data)
    }
    
    // for (num in db.players){
    //     console.log("플레이어",player[])
    //     game0 = {
    //         ...game0,
    //         player
    //     }
        
    // }
    res.send (ranking)
    return 0
});

module.exports =router;
const express = require('express');
const router = express.Router();
const fs = require ('fs');

const dir = './src/db/plays/onGoing/'

    let currentGame;
    let currentStage;

    //get post 차이


router.post('/current', async(req, res)=>{
    currentStage = parseInt (req.query.CURRENT_STAGE)
    return res.send ({"RESULT_CODE": 1})
    //return res.send (JSON.stringify(obj))

})

//current game Set
router.post ('/game', (req,res)=>{

    currentGame = parseInt(req.query.CURRENT_GAME)
    currentStage = 1
 
    return res.send ({"RESULT_CODE": 1})
        
})

router.post('/gameCheck', (req,res)=>{
    return res.send ({"CURRENT_GAME":currentGame, "CURRENT_STAGE": currentStage})
})


//fetch
router.get ('/get', (req,res)=>{
    // fs.readdir (dir, async(err, data)=>{
    //     if (err) return console.log(err);

    //     data.forEach((item, i)=>{
    //         const file = dir + item;
    //         console.log(file)
    //         const data = fs.readFileSync(file, {encoding:"utf-8"})
    //         var db = JSON.parse (data)
    //         currentStage = JSON.stringify(db.current.stage)
    //         currentGame = JSON.stringify(db.current.game)
    //     });
        
    // });
    return res.send ({"CURRENT_GAME":currentGame, "CURRENT_STAGE": currentStage})
})



module.exports =router;
const express = require('express');
const router = express.Router();

let GAME = '-1'
let STAGE = '-1'

router.post ('/onGame', async (req,res) => {
    console.log("game,, before : ", GAME);
    console.log("game,, before q: ", req.query);
    if (req.query.SPECIAL){
        console.log("special");
       GAME = req.query.GAME
    }
    console.log("game,, after : ", GAME);
    return res.send ({'resultcode': GAME})
})


router.post ('/onStage', (req,res) => {
    console.log ("111STAGE before" ,STAGE)
    console.log (typeof(req.query.special))
    if (req.query.special === '2'){
        STAGE = req.query.stage
    }

    console.log ("11STAGE after" ,STAGE)

        
    return res.send ({'resultcode': STAGE})
})


router.post ('/getStage',  (req, res) => {
    return res.send (STAGE)
})

module.exports = router;
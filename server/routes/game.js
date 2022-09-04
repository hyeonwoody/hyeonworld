const express = require('express');
const router = express.Router();

let GAME = '-1'
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

module.exports = router;
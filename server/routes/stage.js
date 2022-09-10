const express = require('express');
const router = express.Router();
const fs = require ('fs');

let STAGE = '-1'
router.post ('/onStage', (req,res) => {
    console.log ("STAGE before" ,STAGE)
    console.log (typeof(req.query.special))
    if (req.query.special === '2'){
        STAGE = req.query.stage
    }
    console.log ("STAGE after" ,STAGE)
    return res.send ({'resultcode': STAGE})
})

module.exports = router;
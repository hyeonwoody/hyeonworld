const express = require('express');
const router = express.Router();
const fs = require ('fs');


const dir = './src/db/plays/onGoing/'



    //get post 차이

router.post('/submit/game0/set', async(req, res)=>{
    var obj = req.query
        fs.writeFileSync(dir+obj.NAME+".json", JSON.stringify(obj));
        return res.send ({"RESULT_CODE": 1})
});

router.post('/submit/game0/get', async(req, res)=>{
    var name = req.query.name
    const data = fs.readFileSync(dir+name+".json", {encoding:"utf-8"})
    let db = JSON.parse (data)
    db.RESULT_CODE = 0;
    return res.send (JSON.stringify(db));
});


module.exports =router;
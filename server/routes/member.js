const express = require('express');
const router = express.Router();
//const db = require('../../src/config/db');
const fs = require ('fs');

let static_value = (function static_func (value){
    let i = value;
    return function() {
        return ++i;
    }
})(0); // 정적 변수 시작을 0으로 초기화

function increment() {
    let cnt = static_value();
    console.log(cnt);
}

router.post ('/onLogin', async (req,res) => {
    const name = req.query.name

    const data = fs.readFileSync("./src/config/db.json", {encoding: "utf-8"})
    let db = JSON.parse(data)
    if (name === "어드minister")
        return res.send({'resultcode': 2})
    else if (name === "화면monitor")
    return res.send({'resultcode': 3})
    for (key in db.members){
        console.log(db.members[key].name," +", key)
        if (name === db.members[key].name){
           increment()
            return res.send({'resultcode': 1})
        }
    }
    return res.send({'resultcode': 0})
})

module.exports = router;
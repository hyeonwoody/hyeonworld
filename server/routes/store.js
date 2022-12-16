// const express = require('express');
// const router = express.Router();
// const fs = require ('fs');

// let static_value = (function static_func(value){
//     let i = value;
//     return function () {
//         return ++i;
//     }
// })

// function increment(name){
//     let cnt = static_value ();
//     console.log ("onLogin, cnt : ", cnt)
// }

// router.post ('/in', (req,res)=>{

//     const name = req.query.NAME
//     const data = fs.readFileSync("./src/db/members.json", {encoding:"utf-8"})
//     let db = JSON.parse (data)
//     if (name === "어드min")
//         return res.send({"resultcode": 2})
//     else if (name === "화면monitor")
//         return res.send({"resultcode": 3})
//     for (key in db.members){
//         if (name === db.members[key].name){
//             increment(name)
//             return res.send ({"resultcode":1})
//         }
//     }
//     return res.send ({"resultcode": 0})
// })

// router.post ('/out', (req,res)=>{
//     const name = req.query.NAME;
    

// })

// module.exports =router;
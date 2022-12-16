const express = require('express');
const router = express.Router();
const fs = require ('fs');


const dir = './src/db/plays/onGoing/'

let static_value = (function static_func(value){
    let i = value;
    return function () {
        return ++i;
    }
})

function deleteOnGoing(){

    fs.readdir ('./src/db/plays/onGoing/', (err, data)=>{
        if (err) return console.log(err);
    
        data.forEach((item, i)=>{
            const file = './src/db/plays/onGoing/' + item;
            fs.unlink (file, (err)=>{
                if (err) return console.log(err);
                console.log('file deleted successfully');
            });
        });
    });
}

router.post ('/in', (req,res)=>{

    const name = req.query.NAME
    const data = fs.readFileSync("./src/db/members.json", {encoding:"utf-8"})
    let db = JSON.parse (data)
    if (name === "어드min")
        return res.send({"resultcode": 2})
    else if (name === "화면monitor")
        return res.send({"resultcode": 3})
    for (key in db.members){
        if (name === db.members[key].name){
            increment(name)
            return res.send ({"resultcode":1})
        }
    }
    return res.send ({"resultcode": 0})
})

router.post ('/admin', (req, res)=>{
    const familySide = req.query.FAMILYSIDE
    const players = req.query.PLAYERS

    
    deleteOnGoing();

    let unix_timestamp = (new Date().getTime())
    
    var date = new Date(unix_timestamp);
    var year = date.getFullYear();
    var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    var month = months[date.getMonth()]
    var day = date.getDate();
    var hours = "0"+date.getHours();
    var minutes = "0"+date.getMinutes();
    //var month = months[date.getMonth()];
    var formattedTime = year+month+day+hours.substr(-2)+ minutes.substr(-2)
    //var formattedTime = date + ' ' + hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2)
    console.log(formattedTime)
    LastInit = formattedTime
    fs.writeFileSync(dir+LastInit+".json", players)
    console.log('file wring complete')
})
module.exports =router;
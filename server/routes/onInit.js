const express = require('express');
const router = express.Router();
const fs = require ('fs');


const dir = './src/db/plays/onGoing/'
    let LastInit


function deleteOnGoing(){

    fs.readdir (dir, (err, data)=>{
        if (err) return console.log(err);
    
        data.forEach((item, i)=>{
            if (i === 0){
                const file = dir + item;
            fs.copyFile(file, './src/db/plays/done/'+item, (err)=>{
                if (err) return console.log(err);
                console.log('file was moved');
            })
            fs.unlink (file, (err)=>{
                if (err) return console.log(err);
                console.log('file deleted successfully');
            });
            }
        });
    });
}

router.post ('/in', (req,res)=>{

    const name = req.query.NAME
    const data = fs.readFileSync("./src/db/members.json", {encoding:"utf-8"})
    let db = JSON.parse (data)
    if (name === "어드min")
        return res.send({"RESULT_CODE": 2})
    else if (name === "화면monitor")
        return res.send({"RESULT_CODE": 3})
    for (key in db.members){
        if (name === db.members[key].name){
            increment(name)
            return res.send ({"RESULT_CODE":1})
        }
    }
    return res.send ({"RESULT_CODE": 0})
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


    var obj = {
        init :{
            familySide: familySide,
            numPlayers: players,
        },
    };
    if (fs.existsSync(dir+formattedTime+".json")){
        console.log("we DO have file")
        fs.writeFileSync(dir+formattedTime+"_"+".json", JSON.stringify(obj))
        LastInit = LastInit+"_"
    }
    else {
        console.log("we dont have file")
        fs.writeFileSync(dir+formattedTime+".json", JSON.stringify(obj))
        LastInit = formattedTime
    }
    obj = {
        players : []
    }
    fs.writeFileSync(dir+"logged.json", JSON.stringify(obj));
    
})
module.exports =router;
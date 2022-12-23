const express = require('express');
const router = express.Router();
const fs = require ('fs');
const dir = './src/db/plays/onGoing/tmp/'


function timeStamp(){
    let unix_timestamp = (new Date().getTime())
    
    var date = new Date(unix_timestamp);
    var year = date.getFullYear();
    var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    var month = months[date.getMonth()]
    var day = date.getDate();
    var hours = "0"+date.getHours();
    var minutes = "0"+date.getMinutes();
    var seconds = "0"+date.getSeconds();
    //var month = months[date.getMonth()];
    var formattedTime = hours.substr(-2)+ minutes.substr(-2) + seconds.substr(-2)
    //var formattedTime = date + ' ' + hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2)
    return formattedTime
}
    //get post 차이
// router.post ('/submit/0/nope', (req,res)=>{
//     var name = req.query.NAME
//     const data = fs.readFileSync(dir+name+".json", {encoding:"utf-8"})
//     db = JSON.parse (data)
//     db.CONFIRM = false
//     fs.writeFileSync (dir+name+".json", JSON.stringify(db))
//     return res.send ({"RESULT_CODE": 0})
// })
// router.post ('/submit/0/confirm', (req,res)=>{
//     var name = req.query.NAME
//     const data = fs.readFileSync(dir+name+".json", {encoding:"utf-8"})
//     db = JSON.parse (data)
//     db.CONFIRM = true
//     fs.writeFileSync (dir+name+".json", JSON.stringify(db))
//     return res.send ({"RESULT_CODE": 0})
// })

router.post('/submit/0/set', async(req, res)=>{
    var obj = req.query
        if (fs.existsSync(dir+obj.NAME+".json")){
            if (!fs.existsSync(dir+"game0")){
                fs.mkdirSync(dir+"game0")
            }
            if (!fs.existsSync(dir+"game0/"+obj.NAME)){
                fs.mkdirSync(dir+"game0/"+obj.NAME)
            }
            console.log("일일")
            await fs.copyFile(dir+obj.NAME+".json", dir+"game0/"+obj.NAME+"/"+obj.NAME+timeStamp()+".json", (err) => {
                if (err) {return res.send({"RESULT_CODE": 0})};
                console.log('source.txt was copied to destination.txt');
              })
              console.log("이")
            await fs.writeFileSync(dir+obj.NAME+".json", JSON.stringify(obj));
            console.log("삼")
        }
        else{
            fs.writeFileSync(dir+obj.NAME+".json", JSON.stringify(obj));
        }
        console.log("사")
        return res.send ({"RESULT_CODE": 0})
});

router.post('/submit/0/get', (req, res)=>{
    var name = req.query.NAME

    console.log("서버 이름은요",name)
    if (fs.existsSync(dir+name+".json")){
        let data = fs.readFileSync(dir+name+".json", {encoding:"utf-8"})
        let db = JSON.parse (data)
        if (db.CONFIRM === 'true'){
            db.RESULT_CODE = 0;
        }
        else if (db.AFALSE === undefined) {
            db.RESULT_CODE = 2;
        }
        return res.send (JSON.stringify(db));
    } 
    else {
        return res.send ({"RESULT_CODE": 1})
    }
});


module.exports =router;
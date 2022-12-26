const express = require('express');
const { flushSync } = require('react-dom');
const fs = require ('fs');
const router = express.Router();

router.post('/0/set', async(req, res)=>{
    const oldPath = './src/db/plays/onGoing/tmp/'
    const newPath = './src/db/plays/onGoing/game0/'
    var combined = []
    if (!fs.existsSync(newPath)){
        fs.mkdirSync(newPath)
    }
    fs.readdir (oldPath, (err, files)=>{
        if (err){
            console.log (err)
        }
        let data
        files.forEach((file,i)=>{
            if (i>2){
                data = fs.readFileSync(oldPath+file, {encoding:"utf-8"})
                combined.push (data)
            }
        })
        fs.writeFileSync (newPath+"final.json", JSON.stringify(combined))   
    })
})

router.post('/0/get', async(req, res)=>{
    console.log ("누구",who)
    const name = who + ".json"
    const data = fs.readFileSync(dir+name, {encoding:"utf-8"})
    return res.send (data)

})

module.exports =router;
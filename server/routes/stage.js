const express = require('express');
const router = express.Router();
const fs = require ('fs');
const { clearInterval } = require('timers');

const dir = './src/db/plays/onGoing/'

    let currentGame;
    let currentStage;
    let time=0
    //get post 차
router.post('/current', async(req, res)=>{
    currentStage = parseInt (req.query.CURRENT_STAGE)
    return res.send ({"RESULT_CODE": 1})
    //return res.send (JSON.stringify(obj))

})

//current game Set
router.post ('/game', (req,res)=>{
    
    currentGame = parseInt(req.query.CURRENT_GAME)
    console.log("게임셋",currentGame)
    return res.send ({"RESULT_CODE": 1})
        
})

router.post('/gameCheck', (req,res)=>{
    return res.send ({"CURRENT_GAME":currentGame, "CURRENT_STAGE": currentStage})
})

router.post ('/get', (req, res)=>{
    // fs.readdir (dir, async(err, data)=>{
    //     if (err) return console.log(err);

    //     data.forEach((item, i)=>{
    //         const file = dir + item;
    //         console.log(file)
    //         const data = fs.readFileSync(file, {encoding:"utf-8"})
    //         var db = JSON.parse (data)
    //         currentStage = JSON.stringify(db.current.stage)
    //         currentGame = JSON.stringify(db.current.game)
    //     });
        
    // });
    return res.send ({"CURRENT_GAME":currentGame, "CURRENT_STAGE": currentStage, "TIME":time++})
})
    //socket.io 사용
    // const io = require('socket.io')(3005, {
    //     cors: {
    //         origin: '*'
    //     }
    // })

    
    // io.on('connection', (socket)=>{
    //     const req = socket.request;
    //     const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
    //     console.log('클라이언트 접속', ip, socket.id)
        
    //     socket.on ('error', (error)=>{
    //         console.error(error);
    //     });
        
    //     socket.interval = setInterval(()=>{
    //         socket.emit('gameStage', {CURRENT_STAGE: currentStage, CURRENT_GAME: currentGame, TIME: time++, ID: socket.id})
    //     }, 3000)


    //     socket.on ('disconnect', ()=>{
    //         console.log('클라이언트 접속 해제', ip, socket.id)
    //         clearInterval(socket.interval)
    //     });

    //     socket.on ('terminate', (socket)=>{
    //         console.log("클라이언트 연결 끊기기", ip)
    //         clearInterval(socket.interval)
    //     })
        
    // })

module.exports =router;
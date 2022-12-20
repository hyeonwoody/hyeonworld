const express = require('express');
const router = express.Router();
const fs = require ('fs');

const dir = './src/db/plays/onGoing/'

    let currentGame;
    let currentStage;

    //get post 차이

const io = require('socket.io')(3000, {
    cors: {
        origin: '*'
    }
})

io.on('connection', socket=>{
    console.log(socket.id)
    console.log("통과는 됐어")
    const req = socket.request;
    const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
    console.log("통과는 됐어", socket.id, "dfdsf", ip)
    io.emit('gameStage', {CURRENT_STAGE: currentStage, CURRENT_GAME: currentGame})
    socket.on ('disconnect', ()=>{
        console.log('클라이언트 접속 해제', scoket.id)
        // clearInterval(socket.interval)
    });
    socket.on ('error', (error)=>{
        console.error(error);
    });
    socket.on ('custom-event', (number,string, obj)=>{
        console.log(number,string,obj);
    });
    socket.interval = setInterval(()=>{
        socket.emit('f','fsd');
    }, 3000)
})

router.post('/current', async(req, res)=>{
    currentStage = parseInt (req.query.CURRENT_STAGE)
    return res.send ({"RESULT_CODE": 1})
    //return res.send (JSON.stringify(obj))

})

//current game Set
router.post ('/game', (req,res)=>{

    currentGame = parseInt(req.query.CURRENT_GAME)
    currentStage = 1
 
    return res.send ({"RESULT_CODE": 1})
        
})

router.post('/gameCheck', (req,res)=>{
    return res.send ({"CURRENT_GAME":currentGame, "CURRENT_STAGE": currentStage})
})


//fetch
router.get ('/get', (req,res)=>{
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
    return res.send ({"CURRENT_GAME":currentGame, "CURRENT_STAGE": currentStage})
})



module.exports =router;
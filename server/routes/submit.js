const express = require('express');
const router = express.Router();



const dir = './src/db/plays/onGoing/'



    //get post 차이

router.post('/0', async(req, res)=>{
    console.log ("aa이름",req.data)
    return res.send ({"RESULT_CODE": 1, "NAME": req.query.NAME})
    //return res.send (JSON.stringify(obj))

})

module.exports =router;
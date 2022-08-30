const db = require('../../config/db');

export function login (name){

    return new Promise ((res, rej)=>{
        return setTimeout(()=>{
            // if (name === "어드minister"){
            //     return res({resultcode: 2}) 
            // }
            // else if (name === "화면screen"){
            //     return res({resultcode: 3})
            // }
            // db.forEach (data=> {
            //     if (name === data.members){
            //         return res({resultcode: 1})
            //     }   
            // })

            console.log(res)
            return res({resultcode: 1})
        },300)
    })
}


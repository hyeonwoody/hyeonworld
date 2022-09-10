import React from "react";

import axios from "axios";


function Monitor(pros) {
    var stage = '-1'
    var game = '-1'
    // axios.post('/stage/onStage', null, {
    //     })
    //             .then (res => {
    //                 stage = res.data.resultcode
    //                 console.log("Monitor stage : ", stage)
    //             })
    //             .catch(err => console.log("fcc",err))

    // axios.post('/game/onGame', null, {

    // })
    //             .then (res => {
    //                 game = res.data.resultcode
    //                 console.log("Monitor  game : ", game)
    //             })
    //             .catch(err => console.log("fcc",err))
    return (
        
        <main >
           <div>Result{pros.whatgame}</div>
           </main>
        
    )
}

export default Monitor;
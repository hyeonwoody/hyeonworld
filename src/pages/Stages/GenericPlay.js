import React from "react";

import Game0 from "../Games/0/Play0"
function Play(props){
    function game(){
        
        switch (props.game){
            case 0:
                return <Game0/>

            default:
                return <div></div>
        }
            
    }
    return (
        <div>
            {game()}

        </div>
    )
}
export default Play;
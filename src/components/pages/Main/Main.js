import React from "react";
import './Main.css';
function Main (pros){
    const isLogin = pros.isLogin
    
    return (
        // <div className="Main">
        //     <h2>
        //         Main Page
        //     </h2>
        //     <input placeholder="Type here" class="input" name="text" type="text"></input>
        // </div>

        <div>
            <main class="contents">
                <article class="card">
                    <h1>진실 혹은 거짓</h1>
                    <span>
                        dfdsfsdfsdfsdf
                    </span>
                </article>
                <article class="card">
                    <h1>다른카드</h1>
                    <span>
                        dfdsfsdfsdfsdf
                    </span>
                </article>
                <article class="card">
                    <h1>다른카드</h1>
                    <span>
                        dfdsfsdfsdfsdf
                    </span>
                </article>
                <article class="card">
                    <h1>다른카드</h1>
                    <span>
                        dfdsfsdfsdfsdf
                    </span>
                </article>
                <article class="card">
                    <h1>다른카드</h1>
                    <span>
                        dfdsfsdfsdfsdf
                    </span>
                </article>
                <article class="card">
                    <h1>다른카드</h1>
                    <span>
                        dfdsfsdfsdfsdf
                    </span>
                </article>
            </main>
        </div>  
    )
}

export default Main;
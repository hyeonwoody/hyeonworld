// const db = {
//     "members": [
//         {"name":"정덕수", "score": 23},
//         {"name":"윤순덕", "score": 3},
//         {"name":"정현우", "score": 3},
//         {"name":"카", "score": 3},
//         {"name":"오랑캐", "score": 3}
//     ],
//     "games":[
//         {"name": "2 truth 1 lie"}
//     ],
//     "va": "nma",
//     "ta": "cxv"
// }

const db = require ("./db.json");

console.log(typeof(db))

module.exports = db;
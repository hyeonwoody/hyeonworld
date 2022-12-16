const fs = require ('fs');

fs.readdir ('../src/db/plays/onGoing/', (err, data)=>{
    if (err) return console.log(err);

    data.forEach((item, i)=>{
        const file = '../src/db/plays/onGoing/' + item;
        fs.unlink (file, (err)=>{
            if (err) return console.log(err);
            console.log('file deleted successfully');
        });
    });
});
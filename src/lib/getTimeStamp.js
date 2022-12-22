export function timeStamp(){
    let unix_timestamp = (new Date().getTime())
    
    var date = new Date(unix_timestamp);
    var year = date.getFullYear();
    var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    var month = months[date.getMonth()]
    var day = date.getDate();
    var hours = "0"+date.getHours();
    var minutes = "0"+date.getMinutes();
    //var month = months[date.getMonth()];
    var formattedTime = year+month+day+hours.substr(-2)+ minutes.substr(-2)
    //var formattedTime = date + ' ' + hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2)
    return formattedTime
}
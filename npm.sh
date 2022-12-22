path='home/recr/Documents/hyeonworld/'
npmPort='1219'
nodeServerPort='3001'
npm_on()
{   
    cd {$path}
    npm start 
}

nodeServer_on()
{
    cd {$path}
    node ./server/server.js &
}

allow_port()
{
    ufw allow 1219
    ufw allow 3005
    ufw allow from 220.86.148.146
}

delete_allow_port()
{   
    ufw delete allow 1219
    ufw delete allow 3005
    ufw delete allow from 220.86.148.146
}

killa()
{
    echo "killing npm"
    `netstat -lntp | grep :$npmPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9`
    echo "killing nodeServer onLog 3001"
    `netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
	nodeServerPort = nodeServerPort + 1
    echo "killing nodeServer onInit 3002"
    `netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
	nodeServerPort = nodeServerPort + 1
    echo "killing nodeServer stage 3003"
    `netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9`
    nodeServerPort = nodeServerPort + 1 
    echo "killing nodeServer 3004"
    `netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
	nodeServerPort = nodeServerPort + 1
    echo "killing nodeServer 3005"
    `netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
    nodeServerPort = nodeServerPort + 1
}

#$# == total number of param
if [ $# -eq 0 ]
then
    npmProcess=`netstat -lntp | grep :$npmPort | awk '{ print $7 }' | cut -d '/' -f1`
    if [ -z "$npmProcess" ] #-z == the case where string is 0
    then
        echo "if npm on"
        npm_on
        echo "if nodeServer on"
        nodeServer_on
    else
        echo "killing"
        killa
        echo "npm on"
        npm_on
        echo "nodeServer on"
        nodeServer_on
    fi
else
    case "$1" in
        clean)
            killa
            ;;
        start)
            allow_port
            nodeServer_on
            npm_on
            ;;
        stop)
            killa
            delete_allow_port
            ;;
        npm)
            echo "npm On"
            npm_on
            ;;
        node)
            echo "nodeServer On"
            nodeServer_on
            ;;
        nodeServer)
            echo "nodeServer On"
            nodeServer_on
            ;;
        *)
            echo "nothing"
            ;;
    esac
fi        

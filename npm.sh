path='/home/recr/Documents/hyeonworld'
npmPort='187'
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

killa()
{
    echo "killing npm"
    `netstat -lntp | grep :$npmPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9`
    echo "killing nodeServer onLog"
    `netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
	#nodeServerPort = nodeServerPort + 1
    #echo "killing nodeServer onInit"
    #`netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
	#nodeServerPort = nodeServerPort + 1
    #echo "killing nodeServer stage"
    #`netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
    #echo "killing nodeServer 3004"
	#nodeServerPort = nodeServerPort + 1
    #`netstat -lntp | grep :$nodeServerPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
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
            nodeServer_on
            npm_on
            ;;
        stop)
            killa
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

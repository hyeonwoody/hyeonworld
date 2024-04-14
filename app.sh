
BASEDIR=$(dirname $0)
path=${BASEDIR}
reactPath=${path}'/src/main/frontend'

javaPort='13000'
reactPort='3000'

react_on()
{
	nohup npm start --prefix ${path}/src/main/frontend >> react.log &
}

java_on()
{
    nohup java -jar ${path}/build/libs/hyeonworld-0.0.1-SNAPSHOT.jar >> java.log &
}

killa()
{
    echo "killing java"
    `netstat -lntp | grep :$javaPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9`
    echo "killing react"
   `netstat -lntp | grep :$reactPort | awk '{ print $7 }' | cut -d '/' -f1 | xargs kill -9` 
}

allow_port()
{
    ufw allow 3000
	ufw allow 13000
}

delete_allow_port()
{
    ufw delete allow 3000
	ufw delete allow 13000
}

build_all()
{
    cd {$path}
    ./gradlew build --exclude-task test
}

if [$# -eq 0 ]; then
    javaProcess=`netstat -lntp | grep :$javaPort | awk '{ print $7 }' | cut -d '/' -f1`
    reactProcess=`netstat -lntp | grep :$reactPort | awk '{ print $7 }' | cut -d '/' -f1`
    if [ -z "$javaProcess" ]; then
        echo "java on"
        java_on
    else
        echo "killing"
        killa
        echo "java on"
        java_on
    fi
else
    case "$1" in
        clean)
            delete_allow_port
            killa
            ;;
		delete_allow)
			delete_allow_port
			;;
        allow)
            allow_port
            ;;
        build)
            build_all
            ;;
        start)
            allow_port
            java_on
            react_on
            ;;
        stop)
            killa
            ;;
        java)
            echo "java on"
            java_on
            ;;
        react)
            echo "react on"
            react_on
            ;;
        *)
            echo "nothing"
            ;;
    esac
fi

import {useEffect, useState} from "react";
import {Hello} from "http/generated";
import useHttpClient from "../http/HttpClient";
import {Blockquote, Loader} from "@mantine/core";


function HelloDemo() {

  const [hello, setHello] = useState<Hello>();
  const [loading, setLoading] = useState(false);
  const httpClient = useHttpClient();

  useEffect(() => {
    const fecth = () => {
      setLoading(true);
      httpClient.hello.helloGet()
          .then((response) => response.data)
          .then((json) => setHello(json))
          .finally(() => setLoading(false));
    }

    fecth()
  }, [])

  if (loading) {
    return <Loader size="sm"/>
  }

  return (<>
    <Blockquote cite={` say: ${hello?.value}`}>
      HelloDemo
    </Blockquote>
  </>);
}

export default HelloDemo;


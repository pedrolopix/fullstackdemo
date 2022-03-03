import {useState} from "react";
import {Hello} from "http/generated";
import useHttpClient from "../http/HttpClient";
import {Blockquote} from "@mantine/core";


function HelloDemo() {
  const [hello, setHello] = useState<Hello>();
  const httpClient = useHttpClient();
  const fecth = () => {
    httpClient.hello.helloGet()
        .then((response) => response.data)
        .then((json) => setHello(json));
  }

  fecth();

  return (
      <Blockquote cite="– {hello?.value}">
        HelloDemo
      </Blockquote>
  );
}

export default HelloDemo;


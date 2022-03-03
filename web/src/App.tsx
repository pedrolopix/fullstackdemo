import HelloDemo from "./components";
import {HttpClientProvider} from "./http/HttpClientContext";
import {MantineProvider} from '@mantine/core'

function App() {

  const apiGatewayUrl = "http://localhost:8080"

  return (
      <HttpClientProvider baseUrl={apiGatewayUrl}>
        <MantineProvider>
          <h1>Demo</h1>
          <hr/>
          <div><HelloDemo/></div>
        </MantineProvider>
      </HttpClientProvider>
  );
}

export default App

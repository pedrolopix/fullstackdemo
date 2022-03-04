import HelloDemo from "./components";
import {HttpClientProvider} from "./http/HttpClientContext";
import {MantineProvider} from '@mantine/core'

function App() {

  const serviceUrl = import.meta.env.VITE_SERVICE_URL as string || "";
  console.log(import.meta.env.MODE);

  return (
      <HttpClientProvider baseUrl={serviceUrl}>
        <MantineProvider>
          <h1>Demo</h1>
          <hr/>
          <div><HelloDemo/></div>
        </MantineProvider>
      </HttpClientProvider>
  );
}

export default App

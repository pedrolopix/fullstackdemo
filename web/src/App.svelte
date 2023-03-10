<script lang="ts">
  import {Router} from 'svelte-router-spa'
  import {routes} from "./routes";
  console.log(routes);
</script>

<div class="h-screen">
  <Router {routes} />
</div>

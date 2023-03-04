<script lang="ts">
  import {Divider, Menu} from "stwui";
  import {sideBar} from "../settings";
  import {tick} from "svelte";
  import {routeIsActive} from "svelte-router-spa";

  export let handleClose: (() => void) | undefined = undefined;

  let active = '';

  function handleClick(item: string) {
    if (handleClose) {
      handleClose();
    }
    tick();
    active = item;
  }

  const menuTitleClass = 'text-xs font-bold text-primary py-2 px-3 h-10';

  // onMount(() => {
  //   active = $page.url.pathname;
  // });
</script>

<Menu {active}>
  {#each sideBar.items as item}
    <div class:active={routeIsActive(item.path)}>
      <Menu.Item
          key={item.path}
          label={item.title}
          href={item.path}
          icon={item.icon}
          on:click={() => handleClick(item.path)}
      />
    </div>  
  {/each}

</Menu>
<Divider/>


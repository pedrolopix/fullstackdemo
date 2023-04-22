<script lang="ts">
  import {Badge, Divider, Menu} from "stwui";
  import {navigateTo} from "svelte-router-spa";
  import type {MenuItem} from "./interfaces";

  export let handleClose: (() => void) | undefined = undefined;

  let active = '';
  let collapsed: Boolean = false
  export let items: MenuItem[] = [];

  function handleClick(item: MenuItem) {
    navigateTo(item.href);
    if (handleClose) {
      handleClose();
    }
    active = item.name;
  }

</script>
<Menu {collapsed} {active}>
  {#each items as item}
    {#if item.children && item.children.length > 0}
      {#if item.data}
        <Menu.Group key={item.name} label={item.label} href={item.href}>
          <Menu.Item.Icon slot="icon" data={item.iconData}/>
          {#each item.children as child}
            <Menu.Group.Item
                key={child.name}
                label={child.label}
                on:click={() => handleClick(item,child)}
            />
          {/each}
        </Menu.Group>
      {:else}
        <Menu.Group key={item.name} label={item.label} href={item.href}>
          {#each item.children as child}
            <Menu.Group.Item
                key={child.name}
                label={child.label}
                on:click={() => handleClick(item,child)}
            />
          {/each}
        </Menu.Group>
      {/if}
    {:else if item.data && item.badge}
      <Menu.Item
          key={item.name}
          label={item.label}
          on:click={() => handleClick(item)}
      >
        <Menu.Item.Icon slot="icon" data={item.iconData}/>
        <Badge slot="extra" type={item.badgeType}>{item.badge}</Badge>
      </Menu.Item>
    {:else if item.iconData}
      <Menu.Item
          key={item.name}
          label={item.label}
          on:click={() => handleClick(item)}
      >
        <Menu.Item.Icon slot="icon" data={item.iconData}/>
      </Menu.Item>
    {:else if item.badge}
      <Menu.Item
          key={item.name}
          label={item.label}
          on:click={() => handleClick(item)}
      >
        <Badge slot="extra" type={item.badgeType}>{item.badge}</Badge>
      </Menu.Item>
    {:else}
      <Menu.Item
          key={item.name}
          label={item.label}
          on:click={() => handleClick(item)}
      />
    {/if}
  {/each}
</Menu>

<Divider/>


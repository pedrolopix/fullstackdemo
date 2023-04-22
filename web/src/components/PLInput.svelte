<script>
  import PLToggle from "./PLToggle.svelte";

  let input;

  export let label = ""
  export let value
  export let type = $$restProps["type"]
  export let labelRight = ""

  function useType(node) {
    node.type = type;
  }

  const name = label || labelRight;
  const inputClass = "w-full bg-white rounded border border-gray-300 focus:border-blue-500 focus:ring-2 focus:ring-blue-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
</script>

<div class="relative mb-4">
  <label for={name} class="leading-7 text-sm text-gray-600">
    {label}
    {#if type === 'toogle'}
      <PLToggle id={name} {value} class={inputClass} bind:on={value}></PLToggle>
    {:else}
      <input bind:this={input} id={name} use:useType bind:value {...$$restProps} class={inputClass}/>
    {/if}

    {#if labelRight}
      {labelRight}
    {/if}
  </label>
</div>

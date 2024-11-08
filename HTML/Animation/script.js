// Create the scene, camera, and renderer
const scene = new THREE.Scene(); // Initialize a new scene object
const camera = new THREE.PerspectiveCamera(
  75, // Field of view (in degrees)
  window.innerWidth / window.innerHeight, // Aspect ratio
  0.1, // Near clipping plane
  1000 // Far clipping plane
); 
const renderer = new THREE.WebGLRenderer(); // Create a WebGL renderer
renderer.setSize(window.innerWidth, window.innerHeight); // Set the size of the renderer to match the window size
document.body.appendChild(renderer.domElement); // Add the renderer's canvas element to the document

// Define the geometry and material for the polygon
const vertices = new Float32Array([
  0.0, 1.0, 0.0, // Top vertex
  0.951, 0.309, 0.0, // Top right vertex
  0.588, -0.809, 0.0, // Bottom right vertex
  -0.588, -0.809, 0.0, // Bottom left vertex
  -0.951, 0.309, 0.0, // Top left vertex
]); // Define the vertices of the polygon

const indices = new Uint16Array([0, 1, 2, 0, 2, 3, 0, 3, 4]); // Define the indices for the polygon faces

const geometry = new THREE.BufferGeometry(); // Create a buffer geometry
geometry.setAttribute(
  "position",
  new THREE.Float32BufferAttribute(vertices, 3)
); // Set the position attribute using the vertices
geometry.setIndex(new THREE.BufferAttribute(indices, 1)); // Set the index attribute using the indices

// Choose material color: Red or Blue
const material = new THREE.MeshBasicMaterial({
  color: 'blue', // Set the color to blue, we can change to red also
  side: THREE.DoubleSide, // Render both sides of the polygon
}); // Create a basic material for the mesh

// Create the polygon mesh
const polygon = new THREE.Mesh(geometry, material); // Create a mesh using the geometry and material
scene.add(polygon); // Add the mesh to the scene

// Position the camera
camera.position.z = 5; // Set the camera position along the z-axis

// Animation loop to rotate the polygon
function animate() {
  requestAnimationFrame(animate); // Request the next frame

  // Rotate the polygon slowly on its x and y axes
  polygon.rotation.x += 0.01; // Increment the rotation around the x-axis
  polygon.rotation.y += 0.01; // Increment the rotation around the y-axis

  renderer.render(scene, camera); // Render the scene from the perspective of the camera
}

// Start the animation
animate(); // Begin the animation loop

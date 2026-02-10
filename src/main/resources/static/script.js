const API_BASE = '/api';
const PARTICIPANT_API = `${API_BASE}/participants`;
const EVENT_API = `${API_BASE}/events`;
const REGISTRATION_API = `${API_BASE}/registrations`;

document.addEventListener('DOMContentLoaded', () => {
    loadParticipants();
    loadEvents();
    loadRegistrations();
    loadEventOptions();
    loadParticipantOptions();
    setupForms();
});


async function loadParticipants() {
    try {
        const response = await fetch(PARTICIPANT_API);
        const participants = await response.json();
        displayParticipants(participants);
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to load participants');
    }
}

function displayParticipants(participants) {
    const list = document.getElementById('participantsList');
    list.innerHTML = '';

    participants.forEach(p => {
        const div = document.createElement('div');
        div.innerHTML = `
            <h3>${p.firstName} ${p.lastName}</h3>
            <p>Email: ${p.email}</p>
            <p>Age: ${p.age} | Gender: ${p.gender}</p>
            <p>T-Shirt: ${p.tShirtSize}</p>
            <button onclick="editParticipant(${p.participantId})">Edit</button>
            <button onclick="deleteParticipant(${p.participantId})">Delete</button>
            <hr>
        `;
        list.appendChild(div);
    });
}

async function editParticipant(id) {
    try {
        const response = await fetch(`${PARTICIPANT_API}/id/${id}`);
        const p = await response.json();

        document.getElementById('participantId').value = p.participantId;
        document.getElementById('firstName').value = p.firstName;
        document.getElementById('lastName').value = p.lastName;
        document.getElementById('email').value = p.email;
        document.getElementById('age').value = p.age;
        document.getElementById('gender').value = p.gender;
        document.getElementById('tShirtSize').value = p.tShirtSize;

        document.querySelector('#participantForm button[type="submit"]').textContent = 'Update Participant';
        document.getElementById('participantCancelBtn').style.display = 'inline-block';
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to load participant');
    }
}

async function deleteParticipant(id) {
    if (confirm('Delete this participant?')) {
        try {
            await fetch(`${PARTICIPANT_API}/delete/${id}`, { method: 'DELETE' });
            loadParticipants();
            loadParticipantOptions();
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to delete participant');
        }
    }
}

async function searchParticipantByEmail() {
    const email = document.getElementById('participantSearch').value.trim();
    if (!email) {
        alert('Enter an email');
        return;
    }

    try {
        const response = await fetch(`${PARTICIPANT_API}/email/${email}`);
        const participant = await response.json();
        displayParticipants([participant]);
    } catch (error) {
        console.error('Error:', error);
        alert('Participant not found');
    }
}


async function loadEvents() {
    try {
        const response = await fetch(EVENT_API);
        const events = await response.json();
        displayEvents(events);
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to load events');
    }
}

function displayEvents(events) {
    const list = document.getElementById('eventsList');
    list.innerHTML = '';

    events.forEach(e => {
        const div = document.createElement('div');
        div.innerHTML = `
            <h3>${e.name}</h3>
            <p>Date: ${new Date(e.dateTime).toLocaleDateString()}</p>
            <p>Location: ${e.location}</p>
            <p>Capacity: ${e.maxCapacity}</p>
            <p>${e.description || 'No description'}</p>
            <button onclick="editEvent(${e.eventId})">Edit</button>
            <button onclick="deleteEvent(${e.eventId})">Delete</button>
            <hr>
        `;
        list.appendChild(div);
    });
}

async function editEvent(id) {
    try {
        const response = await fetch(`${EVENT_API}/id/${id}`);
        const e = await response.json();

        document.getElementById('eventId').value = e.eventId;
        document.getElementById('name').value = e.name;
        if (e.dateTIme) {
                    document.getElementById('dateTime').value = e.dateTime.split('T')[0];
                } else {
                    document.getElementById('dateTime').value = '';
                }
        document.getElementById('location').value = e.location;
        document.getElementById('maxCapacity').value = e.maxCapacity;
        document.getElementById('description').value = e.description || '';

        document.querySelector('#eventForm button[type="submit"]').textContent = 'Update Event';
        document.getElementById('eventCancelBtn').style.display = 'inline-block';
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to load event');
    }
}

async function deleteEvent(id) {
    if (confirm('Delete this event?')) {
        try {
            await fetch(`${EVENT_API}/${id}`, { method: 'DELETE' });
            loadEvents();
            loadEventOptions();
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to delete event');
        }
    }
}


async function loadRegistrations() {
    try {
        const response = await fetch(REGISTRATION_API);
        const registrations = await response.json();
        displayRegistrations(registrations);
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to load registrations');
    }
}

function displayRegistrations(registrations) {
    const list = document.getElementById('registrationsList');
    list.innerHTML = '';

    registrations.forEach(r => {
        const div = document.createElement('div');
        div.innerHTML = `
            <h3>Registration #${r.registrationId}</h3>
            <p>Event ID: ${r.eventId} | Participant ID: ${r.participantId}</p>
            <p>Ticket Type: ${r.ticketType}</p>
            <p>Payment: $${r.paymentAmount || 'N/A'}</p>
            <p>Status: ${r.status || 'Active'}</p>
            <p>Date: ${new Date(r.registrationDate).toLocaleDateString()}</p>
            <button onclick="editRegistration(${r.registrationId})">Edit</button>
            ${r.status !== 'Cancelled' ?
                `<button onclick="cancelRegistration(${r.registrationId})">Cancel</button>` : ''}
            <button onclick="deleteRegistration(${r.registrationId})">Delete</button>
            <hr>
        `;
        list.appendChild(div);
    });
}

async function editRegistration(id) {
    try {
        const response = await fetch(`${REGISTRATION_API}/${id}`);
        const r = await response.json();

        document.getElementById('registrationId').value = r.registrationId;
        document.getElementById('eventId').value = r.eventId;
        document.getElementById('participantId').value = r.participantId;
        document.getElementById('ticketType').value = r.ticketType;
        document.getElementById('status').value = r.status;

        document.querySelector('#registrationForm button[type="submit"]').textContent = 'Update Registration';
        document.getElementById('registrationCancelBtn').style.display = 'inline-block';
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to load registration');
    }
}

async function cancelRegistration(id) {
    if (confirm('Cancel this registration?')) {
        try {
            await fetch(`${REGISTRATION_API}/${id}`, { method: 'PATCH' });
            loadRegistrations();
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to cancel registration');
        }
    }
}

async function deleteRegistration(id) {
    if (confirm('Delete this registration?')) {
        try {
            await fetch(`${REGISTRATION_API}/${id}`, { method: 'DELETE' });
            loadRegistrations();
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to delete registration');
        }
    }
}

async function loadEventOptions() {
    try {
        const response = await fetch(EVENT_API);
        const events = await response.json();

        const select = document.getElementById('eventId');
        const currentValue = select.value;
        select.innerHTML = '<option value="">Select Event</option>';
        events.forEach(e => {
            select.innerHTML += `<option value="${e.eventId}">${e.name}</option>`;
        });
        if (currentValue) select.value = currentValue;
    } catch (error) {
        console.error('Error:', error);
    }
}

async function loadParticipantOptions() {
    try {
        const response = await fetch(PARTICIPANT_API);
        const participants = await response.json();

        const select = document.getElementById('participantId');
        const currentValue = select.value;
        select.innerHTML = '<option value="">Select Participant</option>';
        participants.forEach(p => {
            select.innerHTML += `<option value="${p.participantId}">${p.firstName} ${p.lastName}</option>`;
        });
        if (currentValue) select.value = currentValue;
    } catch (error) {
        console.error('Error:', error);
    }
}

async function applyRegistrationFilter() {
    const filterType = document.getElementById('filterType').value;
    const filterValueSelect = document.getElementById('filterValue');

    if (filterType === 'all') {
        filterValueSelect.style.display = 'none';
        loadRegistrations();
        return;
    }

    filterValueSelect.style.display = 'block';

    try {
        if (filterType === 'event') {
            const response = await fetch(EVENT_API);
            const events = await response.json();
            filterValueSelect.innerHTML = '<option value="">Select Event</option>';
            events.forEach(e => {
                filterValueSelect.innerHTML += `<option value="${e.eventId}">${e.name}</option>`;
            });
        } else if (filterType === 'participant') {
            const response = await fetch(PARTICIPANT_API);
            const participants = await response.json();
            filterValueSelect.innerHTML = '<option value="">Select Participant</option>';
            participants.forEach(p => {
                filterValueSelect.innerHTML += `<option value="${p.participantId}">${p.firstName} ${p.lastName}</option>`;
            });
        }

        filterValueSelect.onchange = async () => {
            const value = filterValueSelect.value;
            if (!value) return;

            const endpoint = filterType === 'event'
                ? `${REGISTRATION_API}/event/${value}`
                : `${REGISTRATION_API}/participant/${value}`;

            const response = await fetch(endpoint);
            const registrations = await response.json();
            displayRegistrations(registrations);
        };
    } catch (error) {
        console.error('Error:', error);
    }
}


function setupForms() {
    document.getElementById('participantForm').addEventListener('submit', async (e) => {
        e.preventDefault();

        const id = document.getElementById('participantId').value;
        const data = {
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            email: document.getElementById('email').value,
            age: parseInt(document.getElementById('age').value),
            gender: document.getElementById('gender').value,
            tShirtSize: document.getElementById('tShirtSize').value
        };

        try {
            if (id) {
                await fetch(`${PARTICIPANT_API}/update/${id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
            } else {
                await fetch(PARTICIPANT_API, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
            }

            resetParticipantForm();
            loadParticipants();
            loadParticipantOptions();
            alert('Participant saved!');
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to save participant');
        }
    });

    document.getElementById('participantCancelBtn').addEventListener('click', resetParticipantForm);

    document.getElementById('eventForm').addEventListener('submit', async (e) => {
        e.preventDefault();

        const id = document.getElementById('eventId').value;
        const data = {
            eventName: document.getElementById('name').value,
            eventDate: document.getElementById('dateTime').value,
            eventLocation: document.getElementById('location').value,
            maxCapacity: parseInt(document.getElementById('maxCapacity').value),
            eventDescription: document.getElementById('description').value
        };

        try {
            if (id) {
                await fetch(`${EVENT_API}/${id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
            } else {
                await fetch(EVENT_API, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
            }

            resetEventForm();
            loadEvents();
            loadEventOptions();
            alert('Event saved!');
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to save event');
        }
    });

    document.getElementById('eventCancelBtn').addEventListener('click', resetEventForm);

    document.getElementById('registrationForm').addEventListener('submit', async (e) => {
        e.preventDefault();

        const id = document.getElementById('registrationId').value;
        const data = {
            eventId: parseInt(document.getElementById('eventId').value),
            participantId: parseInt(document.getElementById('participantId').value),
            ticketType: document.getElementById('ticketType').value
        };

        if (id) {
            data.status = document.getElementById('status').value;
        }

        try {
            if (id) {
                await fetch(`${REGISTRATION_API}/${id}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
            } else {
                await fetch(REGISTRATION_API, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
            }

            resetRegistrationForm();
            loadRegistrations();
            alert('Registration saved!');
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to save registration');
        }
    });

    document.getElementById('registrationCancelBtn').addEventListener('click', resetRegistrationForm);
}

function resetParticipantForm() {
    document.getElementById('participantForm').reset();
    document.getElementById('participantId').value = '';
    document.querySelector('#participantForm button[type="submit"]').textContent = 'Add Participant';
    document.getElementById('participantCancelBtn').style.display = 'none';
}

function resetEventForm() {
    document.getElementById('eventForm').reset();
    document.getElementById('eventId').value = '';
    document.querySelector('#eventForm button[type="submit"]').textContent = 'Add Event';
    document.getElementById('eventCancelBtn').style.display = 'none';
}

function resetRegistrationForm() {
    document.getElementById('registrationForm').reset();
    document.getElementById('registrationId').value = '';
    document.getElementById('status').style.display = 'none';
    document.querySelector('#registrationForm button[type="submit"]').textContent = 'Register Participant';
    document.getElementById('registrationCancelBtn').style.display = 'none';
}